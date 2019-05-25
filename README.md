# Magnoloia Telegram Bot

[![Magnolia compatibility](https://img.shields.io/badge/magnolia--cms-6.0-brightgreen.svg)](https://www.magnolia-cms.com)  [![License Info](http://img.shields.io/badge/license-The%20MIT%20License-brightgreen.svg)](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/blob/master/LICENSE)  [![Telegram](http://trellobot.doomdns.org/telegrambadge.svg)](https://telegram.me/JavaBotsApi)  

This module instantiates a Telegram bot, which allows access to the contents of Magnolia. Initially this bot is designed to work with the community demo version and only allows to search about the tours and the assets, besides publishing pages when the user has logged in through the bot. But in a small way it can be extended to access other workpspaces.

## How does it work

The module instantiates the bot on the Author server through the [API Telegram Bot](https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-chat-session-bot) and accesses the contents of Magnolia through the [REST API of Magnolia](https://documentation.magnolia-cms.com/display/DOCS60/REST+API). Once started, a keyboard with the different commands available will be displayed.

## Features

The following actions are allowed through keyboard commands:
 Start: 
>![start command](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/start.png)

 Search tours: 
>![Search tours](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/tours.png)

> Search assets: 
![Search assets](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/assets.png)

> Login: 
![Login](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/login.png)

> Publish: 
![Publish](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/publish.png)

> Logout: 
![Publish](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/logout.png)

## Configuration

First we must create a new bot through the [Bot Father of Telegram](https://telegram.me/botfather) where we will generate the API key of the bot and the name we want for the bot.
Then in the configuration of the module we will only have to enter the API key and the name that we have generated.

![Config](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/config.png)

Configuración del endpoint, tendremos que especificar la URL de acceso a la API REST de Magnolia.

![Rest](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/raw/master/src/main/resources/magnolia-telegram-bot/webresources/demo/rest.png)

## License
Code licensed under [MIT License](http://opensource.org/licenses/mit-license.html "MIT License").

## Contributors
Magnolia, https://magnolia-cms.com.
Gonzalo Rodríguez Martínez, https://github.com/gonzalo-r-martinez/

## Credit
This module has been developed with the help of the API developed by (https://github.com/rubenlagus) and the icon library developed by (https://github.com/vdurmont/emoji-java)
