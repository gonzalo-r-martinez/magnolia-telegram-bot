# Magnoloia Telegram Bot
[![Magnolia compatibility](https://img.shields.io/badge/magnolia--cms-6.0-brightgreen.svg)](https://www.magnolia-cms.com)  [![License Info](http://img.shields.io/badge/license-The%20MIT%20License-brightgreen.svg)](https://github.com/gonzalo-r-martinez/magnolia-telegram-bot/blob/master/LICENSE)  [![Telegram](http://trellobot.doomdns.org/telegrambadge.svg)](https://telegram.me/JavaBotsApi)  

This module instantiates a Telegram bot, which allows access to the contents of Magnolia. Initially this bot is designed to work with the Magnolia 6.0 Community Edition (CE) demo version and only allows to search about the tours and the assets, besides publishing pages when the user has logged in through the bot. But in a small way it can be extended to access other workpspaces.

## How does it work
El modulo instancia el bot en el servidor Autor mediante la [API](https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-chat-session-bot) y accede a los contenidos de Magnolia a través de la API REST de Magnolia. Una vez iniciado bot se mostrará un teclado con los distintos comandos disponibles

The module instantiates the bot on the Author server through the  [API](https://github.com/rubenlagus/TelegramBots/tree/master/telegrambots-chat-session-bot) and accesses the contents of Magnolia through the [REST API of Magnolia](https://documentation.magnolia-cms.com/display/DOCS60/REST+API). Once started, a keyboard with the different commands available will be displayed.

## Features
The following actions are allowed through keyboard commands:
> Search tours: 
 - ##### Search assets:
 - ##### Login:
 - ##### Publish:
 - ##### Logout:
 - ##### Help:

## Configuration
Primero deberemos crear un bot nuevo a través del Father bot de Telegram dónde generaremos la API key del bot y el nombre que deseamos para el bot.
Después en la configuración del módulo solo tendremos que introducir la API key y el nombre que hemos generado.
Configuración del endpoint, tendremos que especificar la URL de acceso a la API REST de Magnolia.

## License
Code licensed under [MIT License](http://opensource.org/licenses/mit-license.html "MIT License").

## Contributors
Magnolia, https://magnolia-cms.com.
Gonzalo Rodríguez Martínez, https://github.com/gonzalo-r-martinez/

## Credit
This module has been developed with the help of the API developed by (https://github.com/rubenlagus) and the icon library developed by (https://github.com/vdurmont/emoji-java)
