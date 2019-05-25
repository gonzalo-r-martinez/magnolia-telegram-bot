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
package org.magnolia.telegram.bot.module;

import org.magnolia.telegram.bot.MgnlTelegramBotSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;
import info.magnolia.objectfactory.Components;
import info.magnolia.templating.functions.TemplatingFunctions;

/**
 * This class is optional and represents the configuration for the
 * magnolia-telegram-bot module. By exposing simple getter/setter/adder methods,
 * this bean can be configured via content2bean using the properties and node
 * from <tt>config:/modules/magnolia-telegram-bot</tt>. If you don't need this,
 * simply remove the reference to this class in the module descriptor xml.
 */
public class MgnlTelegramBotModule implements ModuleLifecycle {

    private static final Logger log = LoggerFactory.getLogger(MgnlTelegramBotModule.class);

    private String botName;
    private String botToken;

    /*
     * (non-Javadoc)
     * 
     * @see info.magnolia.module.ModuleLifecycle#start(info.magnolia.module.
     * ModuleLifecycleContext)
     */
    @Override
    public void start(ModuleLifecycleContext moduleLifecycleContext) {

        TemplatingFunctions cmsfn = Components.getComponent(TemplatingFunctions.class);

        // Only start the bot in the author instance
        if (cmsfn.isAuthorInstance()) {
            // Initialize Api Context
            ApiContextInitializer.init();
            // Instantiate Telegram Bots API
            TelegramBotsApi botsApi = new TelegramBotsApi();
            // Register Magnolia bot
            try {
                botsApi.registerBot(new MgnlTelegramBotSession());
            } catch (TelegramApiException e) {
                log.error("Error init telegram bot", e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see info.magnolia.module.ModuleLifecycle#stop(info.magnolia.module.
     * ModuleLifecycleContext)
     */
    @Override
    public void stop(ModuleLifecycleContext moduleLifecycleContext) {
        // Stop module
    }

    /**
     * Gets the bot name.
     *
     * @return the bot name
     */
    public String getBotName() {
        return botName;
    }

    /**
     * Sets the bot name.
     *
     * @param botName the new bot name
     */
    public void setBotName(String botName) {
        this.botName = botName;
    }

    /**
     * Gets the bot token.
     *
     * @return the bot token
     */
    public String getBotToken() {
        return botToken;
    }

    /**
     * Sets the bot token.
     *
     * @param botToken the new bot token
     */
    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

}
