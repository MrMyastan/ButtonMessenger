[![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT) [![Tested on: Spigot 1.16.1](https://img.shields.io/badge/Tested%20on-Spigot%201.16.1-yellow.svg?logo=minecraft)](https://www.spigotmc.org/) [![Languag: Java](https://img.shields.io/badge/Language-Java-red?logo=java)](https://www.java.com/en/) ![API Jar: 1.16.1-R0.1](https://img.shields.io/badge/API%20Jar-1.16.1--R0.1-blueviolet) ![Version: v1.3](https://img.shields.io/badge/Version-v1.0-blue)
# ButtonMessenger
A Spigot-API plugin for making buttons send messages to Discord webhook links and broadcasted on the server!\
Made as commission for the wonderful [wilm0x42](https://github.com/wilm0x42)!\
You can feel free to modify the code for your own use (just make sure to credit me for my code), and use the plugin for your own server!
Any suggestions for improvements or features are welcome!
# Compatibility
I've tested the plugin using a Spigot 1.16.1 server and made the plugin using the 1.16.1-R0.1 Spigot-API jar, and I compiled the plugin with Java 8
# Features
- /hook [webhook link] [message to send to webhook and broadcast]
  - Look at a button and use this command to add the webhook and message the send to the webhook/broadcast on the server
  - message can use $Player and $Time placeholders
  - ops only by default, permission is buttonmessenger.hook
- /unhook
  - look at button and use this command to remove the webhook/message to broadcast
  - ops only by default, permission is buttonmessenger.unhook
- Buttons save across restarts
# Things to note
- If someone breaks a hooked button as long as they replace the button on the same block the hook will still work
- Breaking a button does not unhook it, only the command does
- Probably easiest to write the message first and then paste webhook link so you aren't writing the message at the edge of the message bar
- Hooked buttons need to have a webhook to send to at the mo, they can't just broadcast a message on the server (also they will always broadcast the message on the server), the two always go together
- When getting the block a player is looking at it uses imprecise targeting, so every block be it sand or torches etc is treated as 1x1x1


Testin