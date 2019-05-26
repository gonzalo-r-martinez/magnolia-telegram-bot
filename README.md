# Magnolia Telegram Bot

[![Magnolia compatibility](https://img.shields.io/badge/magnolia--cms-6.0-brightgreen.svg)](https://www.magnolia-cms.com)  [![License Info](http://img.shields.io/badge/license-The%20MIT%20License-brightgreen.svg)](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/blob/master/LICENSE)  [![Telegram](http://trellobot.doomdns.org/telegrambadge.svg)](https://telegram.me/JavaBotsApi)  

This module instantiates a Telegram Bot, which accesses to the contents of Magnolia. Initially, this bot is designed to work with the community demo version. It only allows to search the tours and assets and also to publish pages when the user has logged in through the bot. In addition, it can be easily extended to access other workpspaces.

## How does it work

The module instantiates the bot on the Author server through the [API Telegram Bot](https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-chat-session-bot) and accesses the contents of Magnolia through the [REST API of Magnolia](https://documentation.magnolia-cms.com/display/DOCS60/REST+API). Once it started, a keyboard with the different commands available will be displayed.

## Features

The following actions are allowed through keyboard commands:

Start: 
>![start command](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/start.png)

Search tours: 
>![Search tours](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/tours.png)

Search assets: 
>![Search assets](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/assets.png)

Login: 
>![Login](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/login.png)

Publish: 
>![Publish](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/publish.png)

Logout: 
>![Publish](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/logout.png)

## Configuration

First, we create a new bot using the [Bot Father of Telegram](https://telegram.me/botfather). We generate an API key for the bot and the desired bot name. Then, we add the API key and the name that we have generated in the module configuration.

![Config](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/config.png)

Finally, we configure the URL to access the endopoint of the Magnolia REST API.

![Rest](https://github.com/gonzalo-r-martinez/telegram-bot-magnolia/raw/master/src/main/resources/telegram-bot-magnolia/webresources/demo/rest.png)

## License
Code licensed under [MIT License](http://opensource.org/licenses/mit-license.html "MIT License").

## Contributors
Magnolia, https://magnolia-cms.com.
Gonzalo Rodríguez Martínez, https://github.com/gonzalo-r-martinez/

## Credit
This module has been developed with the help of the API developed by (https://github.com/rubenlagus) and the icon library developed by (https://github.com/vdurmont/emoji-java)
