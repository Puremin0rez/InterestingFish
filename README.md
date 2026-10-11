# InterestingFish

**Compatible with Minecraft 26.3+ (Tested on Minecraft 26.3)**

This is a continuation / maintained version of the original plugin created by lazertester from Shotbow network.
The original has not been updated since 2013.

The goal of this project is to maintain the original projects fully working operation for modern versions of the game
as well as add new configurable features for people who want to do more with the plugin.

This continuation is entirely made in mind for my own personal server - but fully configurable with a default of original
behavior.

## What's different?

This is a list of everything that has been altered from the original from a users perspective:
* Full support for Minecraft 26.3+
* Added a configurable chance for breeds (and therefore, interesting fish)
* Added a configurable option to require luck of the sea enchant on a fishing rod to catch interesting fish
* Added a configurable option to exclude luck of the sea enchant on a fishing rod to catch interesting fish
* Added a configurable option to use player nickname instead of username on fish labels
* Added a configurable option to show fish label when right clicking an item frame
* Added [MiniMessage support](https://docs.papermc.io/adventure/minimessage/format/) so that you can use fancy effects and full RGB color for fish descriptors and breeds
* Added a permission `interestingfish.catch` (default: true) for controling if a player can catch an interesting fish
* Fixed fish weights ignoring `minWeightModifier`, weights now always land between the min and max modifiers
* Changed `rollWeight` so that chances are proportional to it, a `rollWeight` of 0 disables that descriptor or breed
* Rewrote the configuration system with comments explaining every option, new options are added to your existing config and legacy color codes are converted to MiniMessage

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
