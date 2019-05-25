/**
 * This file Copyright (c) 2015-2019 Magnolia International
 * Ltd.  (http://www.magnolia-cms.com). All rights reserved.
 *
 *
 * This file is dual-licensed under both the Magnolia
 * Network Agreement and the GNU General Public License.
 * You may elect to use one or the other of these licenses.
 *
 * This file is distributed in the hope that it will be
 * useful, but AS-IS and WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE, or NONINFRINGEMENT.
 * Redistribution, except as permitted by whichever of the GPL
 * or MNA you select, is prohibited.
 *
 * 1. For the GPL license (GPL), you can redistribute and/or
 * modify this file under the terms of the GNU General
 * Public License, Version 3, as published by the Free Software
 * Foundation.  You should have received a copy of the GNU
 * General Public License, Version 3 along with this program;
 * if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * 2. For the Magnolia Network Agreement (MNA), this file
 * and the accompanying materials are made available under the
 * terms of the MNA which accompanies this distribution, and
 * is available at http://www.magnolia-cms.com/mna.html
 *
 * Any modifications to this file must keep this entire header
 * intact.
 *
 */
package org.magnolia.telegram.bot;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.session.Session;
import org.magnolia.telegram.bot.beans.UserBean;
import org.magnolia.telegram.bot.beans.tour.Result;
import org.magnolia.telegram.bot.jcr.MgnlTelegramJCR;
import org.magnolia.telegram.bot.module.MgnlTelegramBotModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import com.vdurmont.emoji.EmojiParser;

import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.objectfactory.Components;

/**
 * The Class MgnlTelegramBotSession.
 */
public class MgnlTelegramBotSession extends TelegramLongPollingSessionBot {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(MgnlTelegramBotSession.class);

    /** The bot name. */
    private String botName;

    /** The bot token. */
    private String botToken;

    /** The Constant PARAMETER_USER. */
    private static final String PARAMETER_USER = "user";

    /** The Constant PARAMETER_STATUS. */
    private static final String PARAMETER_STATUS = "status";

    /** The Constant STATUS_WAIT_COMMAND. */
    private static final Integer STATUS_WAIT_COMMAND = 1;

    /** The Constant STATUS_LOGIN_INPUT. */
    private static final Integer STATUS_LOGIN_INPUT = 3;

    /** The Constant STATUS_TOUR_INPUT. */
    private static final Integer STATUS_TOUR_INPUT = 4;

    /** The Constant STATUS_DAM_INPUT. */
    private static final Integer STATUS_DAM_INPUT = 5;

    /** The Constant STATUS_PUBLISH_INPUT. */
    private static final Integer STATUS_PUBLISH_INPUT = 6;

    /** The i 18 n. */
    private final SimpleTranslator i18n;

    /**
     * Instantiates a new mgnl telegram bot session.
     */
    public MgnlTelegramBotSession() {
        super();
        MgnlTelegramBotModule modulo = Components.getComponent(MgnlTelegramBotModule.class);
        botName = modulo.getBotName();
        botToken = modulo.getBotToken();
        this.i18n = Components.getComponent(SimpleTranslator.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.telegram.telegrambots.meta.generics.LongPollingBot#getBotUsername()
     */
    @Override
    public String getBotUsername() {
        return botName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.telegram.telegrambots.bots.DefaultAbsSender#getBotToken()
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.telegram.telegrambots.session.TelegramLongPollingSessionBot#
     * onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update,
     * java.util.Optional)
     */
    @Override
    public void onUpdateReceived(Update update, Optional<Session> botSession) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            log.debug("onUpdateReceived hay mensajes");
            // Set variables
            String messageText = update.getMessage().getText();
            log.debug("onUpdateReceived Mesnaje {}", messageText);
            long chatId = update.getMessage().getChatId();
            log.debug("onUpdateReceived chat_id {}", chatId);
            Integer messageId = update.getMessage().getMessageId();
            Session session = null;
            Integer status = null;

            Optional<Session> sesionOptional = getSession(update.getMessage());
            if (sesionOptional.isPresent()) {
                session = sesionOptional.get();
                if (session != null) {
                    status = session.getAttribute(PARAMETER_STATUS) != null
                            ? (Integer) session.getAttribute(PARAMETER_STATUS)
                            : STATUS_WAIT_COMMAND;
                }
            }

            if (STATUS_WAIT_COMMAND.equals(status)) {
                if (messageText.equals("/start")) {
                    startCommand(chatId, session);
                } else if (messageText.equals("/markup")) {
                    markupCommand(chatId);
                } else if (messageText.equals(i18n.translate("magnolia-telegram-bot.keyboard.search.assets"))) {
                    damCommand(session, chatId, messageId);
                } else if (messageText.equals(i18n.translate("magnolia-telegram-bot.keyboard.search.tours"))) {
                    toursCommand(session, chatId, messageId);
                } else if (messageText.equals(i18n.translate("magnolia-telegram-bot.keyboard.publish.website"))) {
                    publishCommand(session, chatId, messageId);
                } else if (messageText.equals(i18n.translate("magnolia-telegram-bot.keyboard.login"))) {
                    loginCommand(session, chatId, messageId);
                } else if (messageText
                        .equals(EmojiParser.parseToUnicode(i18n.translate("magnolia-telegram-bot.keyboard.logout")))) {
                    logoutCommand(session, chatId, messageId);
                } else if (messageText.equals(i18n.translate("magnolia-telegram-bot.keyboard.help"))) {
                    helpCommand(session, chatId, messageId);
                } else {
                    unknowCommand(session, chatId, messageId);
                }
            } else if (STATUS_LOGIN_INPUT.equals(status)) {
                processLogin(session, chatId, messageText);
            } else if (STATUS_TOUR_INPUT.equals(status)) {
                toursSearch(session, chatId, messageText);
            } else if (STATUS_DAM_INPUT.equals(status)) {
                damSearch(session, chatId, messageText);
            } else if (STATUS_PUBLISH_INPUT.equals(status)) {
                publishPage(session, chatId, messageText);
            }

        }
    }

    /**
     * Start command.
     *
     * @param chatId  the chat id
     * @param session the session
     */
    private void startCommand(long chatId, Session session) {
        log.debug("startCommand Start");
        log.debug("startCommand chatId {}", chatId);
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText("Welcome to Magnolia CMS bot");
        message.setReplyMarkup(markupMain(session));
        sendText(message);
        log.debug("startCommand End");
    }

    /**
     * Unknow command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void unknowCommand(Session session, long chatId, Integer messageId) {
        log.debug("unknowCommand Start");
        log.debug("unknowCommand chatId {}", chatId);
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText("Unknown command").setReplyToMessageId(messageId);

        message.setReplyMarkup(markupMain(session));
        sendText(message);
        log.debug("unknowCommand End");
    }

    /**
     * Markup main.
     *
     * @param session the session
     * @return the reply keyboard markup
     */
    private ReplyKeyboardMarkup markupMain(Session session) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        UserBean user = null;

        if (session != null) {
            user = (UserBean) session.getAttribute(PARAMETER_USER);
        }

        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        row1.add(i18n.translate("magnolia-telegram-bot.keyboard.search.tours"));
        row1.add(i18n.translate("magnolia-telegram-bot.keyboard.search.assets"));

        row2.add(i18n.translate("magnolia-telegram-bot.keyboard.help"));
        if (user != null) {
            row1.add(i18n.translate("magnolia-telegram-bot.keyboard.publish.website"));
            row2.add(EmojiParser.parseToUnicode(":person_frowning: " + user.getUsername()));
            row2.add(EmojiParser.parseToUnicode(i18n.translate("magnolia-telegram-bot.keyboard.logout")));
        } else {
            row2.add(i18n.translate("magnolia-telegram-bot.keyboard.login"));
        }

        keyboard.add(row1);
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    /**
     * Markup command.
     *
     * @param chatId the chat id
     */
    private void markupCommand(long chatId) {
        SendMessage message = new SendMessage() // Create a messageobject object
                .setChatId(chatId).setText("Here is your keyboard");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need
        // something else than text
        row.add("Row 1 Button 1");
        row.add("Row 1 Button 2");
        row.add("Row 1 Button 3");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Row 2 Button 1");
        row.add("Row 2 Button 2");
        row.add("Row 2 Button 3");
        // Add the second row to the keyboard
        keyboard.add(row);

        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);
        sendText(message);
    }

    /**
     * Dam command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void damCommand(Session session, long chatId, Integer messageId) {

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.dam"))
                .setReplyToMessageId(messageId);

        session.setAttribute(PARAMETER_STATUS, STATUS_DAM_INPUT);

        sendText(message);
    }

    /**
     * Dam search.
     *
     * @param session     the session
     * @param chatId      the chat id
     * @param messageText the message text
     */
    private void damSearch(Session session, long chatId, String messageText) {
        MgnlTelegramJCR jcrUtil = new MgnlTelegramJCR();

        List<org.magnolia.telegram.bot.beans.asset.Result> assets = jcrUtil
                .getAssetRest((UserBean) session.getAttribute(PARAMETER_USER), messageText);

        if (assets.isEmpty()) {
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(EmojiParser.parseToUnicode(i18n.translate("magnolia-telegram-bot.message.dam.error")));
            message.setReplyMarkup(markupMain(session));
            sendText(message);
        } else {
            for (org.magnolia.telegram.bot.beans.asset.Result asset : assets) {
                sendAsset(asset, chatId);
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(assets.size() + " " + i18n.translate("magnolia-telegram-bot.message.dam.result"));
            message.setReplyMarkup(markupMain(session));
            sendText(message);
        }
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText("");
        message.setReplyMarkup(markupMain(session));
        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);
    }

    /**
     * Send asset.
     *
     * @param asset  the asset
     * @param chatId the chat id
     */
    private void sendAsset(org.magnolia.telegram.bot.beans.asset.Result asset, long chatId) {

        String caption = asset.getName() + "\n" + "Activation Status: " + getStatusCode(asset.getMgnlActivationStatus())
                + "\nLast Modified: " + asset.getMgnlLastModified();

        MgnlTelegramJCR jcrUtil = new MgnlTelegramJCR();

        InputStream file = jcrUtil.getContentStream(asset.getId());

        SendPhoto msg = new SendPhoto().setChatId(chatId).setPhoto(asset.getName(), file)
                .setCaption(EmojiParser.parseToUnicode(caption));
        sendPhoto(msg);

        IOUtils.closeQuietly(file);
    }

    /**
     * Tours command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void toursCommand(Session session, long chatId, Integer messageId) {
        // /tours
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.tours"))
                .setReplyToMessageId(messageId);

        session.setAttribute(PARAMETER_STATUS, STATUS_TOUR_INPUT);

        sendText(message);
    }

    /**
     * Tours search.
     *
     * @param session     the session
     * @param chatId      the chat id
     * @param messageText the message text
     */
    private void toursSearch(Session session, long chatId, String messageText) {
        MgnlTelegramJCR jcrUtil = new MgnlTelegramJCR();

        List<Result> tours = jcrUtil.getTourRest((UserBean) session.getAttribute(PARAMETER_USER), messageText);

        if (tours.isEmpty()) {
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(EmojiParser.parseToUnicode(i18n.translate("magnolia-telegram-bot.message.tours.error")));
            message.setReplyMarkup(markupMain(session));
            sendText(message);
        } else {
            for (Result tour : tours) {
                sendTour(tour, chatId);
            }
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(tours.size() + " " + i18n.translate("magnolia-telegram-bot.message.tours.result"));
            message.setReplyMarkup(markupMain(session));
            sendText(message);
        }

        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);
    }

    /**
     * Send tour.
     *
     * @param tour   the tour
     * @param chatId the chat id
     */
    private void sendTour(Result tour, long chatId) {

        String caption = tour.getName() + "\n" + "Activation Status: " + getStatusCode(tour.getMgnlActivationStatus())
                + "\nLast Modified: " + tour.getMgnlLastModified();
        String imagen = tour.getImage();
        if (imagen != null) {
            imagen = imagen.substring(4);
        }

        MgnlTelegramJCR jcrUtil = new MgnlTelegramJCR();

        InputStream file = jcrUtil.getContentStream(imagen);

        SendPhoto msg = new SendPhoto().setChatId(chatId).setPhoto(tour.getName(), file)
                .setCaption(EmojiParser.parseToUnicode(caption));
        sendPhoto(msg);

        IOUtils.closeQuietly(file);
    }

    /**
     * Publish command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void publishCommand(Session session, long chatId, Integer messageId) {

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("Enter the path of the page")).setReplyToMessageId(messageId);

        session.setAttribute(PARAMETER_STATUS, STATUS_PUBLISH_INPUT);

        sendText(message);
    }

    /**
     * Publish page.
     *
     * @param session     the session
     * @param chatId      the chat id
     * @param messageText the message text
     */
    private void publishPage(Session session, long chatId, String messageText) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.publish.correct"));
        message.setReplyMarkup(markupMain(session));
        MgnlTelegramJCR jcrUtil = new MgnlTelegramJCR();
        if (!jcrUtil.publishWebsite((UserBean) session.getAttribute(PARAMETER_USER), messageText)) {
            message.setText(i18n.translate("magnolia-telegram-bot.message.publish.error"));
        }
        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);
        sendText(message);
    }

    /**
     * Login command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void loginCommand(Session session, long chatId, Integer messageId) {

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.login"))
                .setReplyToMessageId(messageId);

        session.setAttribute(PARAMETER_STATUS, STATUS_LOGIN_INPUT);

        sendText(message);
    }

    /**
     * Logout command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void logoutCommand(Session session, long chatId, Integer messageId) {

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.logout"))
                .setReplyToMessageId(messageId);

        session.removeAttribute(PARAMETER_USER);

        message.setReplyMarkup(markupMain(session));

        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);

        sendText(message);
    }

    /**
     * Help command.
     *
     * @param session   the session
     * @param chatId    the chat id
     * @param messageId the message id
     */
    private void helpCommand(Session session, long chatId, Integer messageId) {

        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId).setText(i18n.translate("magnolia-telegram-bot.message.help"))
                .setReplyToMessageId(messageId);
        message.setReplyMarkup(markupMain(session));
        session.removeAttribute(PARAMETER_USER);
        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);

        sendText(message);
    }

    /**
     * Process login.
     *
     * @param session     the session
     * @param chatId      the chat id
     * @param messageText the message text
     */
    private void processLogin(Session session, long chatId, String messageText) {
        String[] arrayString = messageText.split(":");
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId);
        if (arrayString.length != 2) {
            message.setText(i18n.translate("magnolia-telegram-bot.message.login.error"));
        } else {
            message.setText(i18n.translate("magnolia-telegram-bot.message.login.ok"));
            session.setAttribute(PARAMETER_USER, new UserBean(arrayString[0], arrayString[1]));
        }
        session.setAttribute(PARAMETER_STATUS, STATUS_WAIT_COMMAND);
        message.setReplyMarkup(markupMain(session));
        sendText(message);
    }

    /**
     * Send text.
     *
     * @param message the message
     */
    private void sendText(SendMessage message) {
        log.debug("sendText Start");
        log.debug("sendText message {}", message);
        try {
            execute(message); // Sending our message object to uSer
            log.debug("onUpdateReceived enviamos mensaje de vuelta");
        } catch (TelegramApiException e) {
            log.error("Error en la respuesta del mensaje", e);
        }
        log.debug("sendText Start");
    }

    /**
     * Send photo.
     *
     * @param message the message
     */
    private void sendPhoto(SendPhoto message) {
        log.debug("sendPhoto Start");
        log.debug("sendPhoto message {}", message);
        try {
            execute(message); // Sending our message object to uSer
            log.debug("onUpdateReceived enviamos mensaje de vuelta");
        } catch (TelegramApiException e) {
            log.error("Error en la respuesta del mensaje", e);
        }
        log.debug("sendPhoto Start");
    }

    /**
     * Gets the status code.
     *
     * @param status the status
     * @return the status code
     */
    private String getStatusCode(String status) {

        if ("true".equals(status)) {
            return ":white_check_mark:";
        } else {
            return ":no_entry:";
        }
    }

}
