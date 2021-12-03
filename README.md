# InterestingFish

**Compatible with Minecraft 1.13+ (Tested on Minecraft 1.18)**

This is a continuation / maintained version of the original plugin created by lazertester from Shotbow network.
The original has not been updated since 2013.

The goal of this project is to maintain the original projects fully working operation for modern versions of the game
as well as add new configurable features for people who want to do more with the plugin.

This continuation is entirely made in mind for my own personal server - but fully configurable with a default of original
behavior.

## What's different?

This is a list of everything that has been altered from the original from a users perspective:
* Full support for Minecraft 1.13+
* Added a configurable chance for breeds (and therefore, interesting fish)
* Added a configurable option to require luck of the sea enchant on a fishing rod to catch interesting fish
* Added a configurable option to exclude luck of the sea enchant on a fishing rod to catch interesting fish
* Added a configurable option to use player nickname instead of username on fish labels
* Added a configurable option to show fish label when right clicking an item frame
* Added [MiniMessage support](https://docs.adventure.kyori.net/minimessage.html#format) so that you can use fancy effects and full RGB color for fish descriptors and breeds
* Added a permission `interestingfish.catch` (default: true) for controling if a player can catch an interesting fish

Note that due to the flawed configuration system of the plugin, any new config options will reset your existing config.
Therefore, your existing configs are not directly compatible with this continuation. You can simply merge the new options
into your existing one. I hope to someday resolve this issue by rewriting how the plugin handles configuration so that
I can also add comments explaining configuration options.

## How do I obtain it?

You can download stable releases via Github Releases, [located here.](https://github.com/Puremin0rez/InterestingFish/releases)

You can download development builds via Github Actions, [located here.](https://github.com/Puremin0rez/InterestingFish/actions?query=branch%3Amaster+is%3Asuccess) (Github Account Required)

You can compile it by running the following command in the project directory:

```
./gradlew clean build
```

(The jar file will be located in `/build/libs/`)

## Can I use your code?

The original project was released with the "do whatever the **** you want" license.

This project is licensed as [GPL-3.0](LICENSE) and may be used in any way that respects the aforementioned license.

## Acknowledgements

* Many thanks to [lazertester](https://github.com/lazertester) from [Shotbow Network](https://shotbow.net) for releasing his code to the public and giving it an awesome license. You rock! :)
* Myself for probably being the only server running that actually has used this plugin in non stop operation since 2013.
* [Contributors](https://github.com/Puremin0rez/InterestingFish/graphs/contributors) for helping improve the project.
* You, for reading this and checking out the project.
