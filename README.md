# InterestingFish

**Compatible with Minecraft 1.13+ (Tested on Minecraft 1.15)**

This is a continuation / maintained version of the original plugin created by lazertester from the Shotbow network.
The original has not been updated since 2013.

The goal of this project is to maintain the original projects fully working operation for modern versions of the game
as well as add new configurable features for people who want to do more with the plugin.

This continuation is entirely made in mind for my own personal server - but fully configurable with a default of original
behavior.

## What's different?

This is a list of everything that has been altered from the original from a users perspective:
* Updated to Minecraft 1.15 (to properly support 1.13 flattening)
* Added a configurable chance for breeds (and therefore, interesting fish)
* Added a configurable option to require luck of the sea enchant on a fishing rod to catch interesting fish

Note that due to the flawed configuration system of the plugin, any new config options will reset your existing config.
Therefore, your existing configs are not directly compatible with this continuation. You can simply merge the new options
into your existing one. I hope to someday resolve this issue by rewriting how the plugin handles configuration so that
I can also add comments explaining configuration options.

## How do I obtain it?

You can compile it yourself by using Maven and simply running the following command in the project folder:
(The jar file will be located in /targets/)

```
mvn
```

You can also download the latest pre compiled binary over on the releases section of Github, located here:

https://github.com/Puremin0rez/InterestingFish/releases

## Can I use your code?

The original project was released with the "do whatever the **** you want" license.
I like it, so I've opted for a more official license of MIT. Refer to the project license here: [LICENSE.md](LICENSE.md)

Basically, yeah, do whatever you'd like. Giving credit would be appreciated :)
## Acknowledgements

* Many thanks to lazertester from The Shotbow Network for releasing his code to the public and giving it an awesome license. You rock! :)
* Myself for probably being the only server running that actually has used this plugin in non stop operation since 2013.
* You, for reading this and checking out the project.
