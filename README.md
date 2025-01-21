# GachaPlugin
### GachaPlugin is a Minecraft plugin that implements a simple and interactive "Gacha" system, allowing players to receive random rewards when they use a special command. With a custom graphical user interface (GUI), the plugin offers a fun way to reward players with different rarity items.

## Features
- /gacha Command: Players can open the Gacha interface and participate in the reward drawing by using the /gacha command.
- Graphical User Interface (GUI): The interface displays a "rolling" animation before revealing the final reward, which can be any item from the configured rewards.
- Random Rewards: The reward system is based on configured chances, with each item having a probability of being drawn.
- Visual Animation: During the Gacha roll, the items on the interface change randomly until the final reward is revealed.
- Diverse Rewards: I used several items when creating the plugin, but in the end the choice is completely yours. 

## How to Use
### 1. Installation:

- Download the .jar file and place it in the plugins folder of your Minecraft server.
- Restart the server or reload the plugins with /reload.
### 2. Command:

In-game, players can use the /gacha command to open the Gacha reward interface.
### 3. How it Works:

- When the command is executed, the graphical interface will open with an animation of random items spinning.
- After a few seconds, the drawing will finish, and the player will receive a random reward.
- The system has configurable chances for each item, with rarer rewards having a lower probability of appearing.
## Code Structure
### Main Class (GachaPlugin)
The main class of the plugin is responsible for registering events and commands. Upon initialization (onEnable), it sets up the plugin’s event listeners and commands.

- Events: The GachaGUI is registered as an event listener, allowing the plugin to handle player interactions with the Gacha GUI.
- /gacha Command: The /gacha command is registered and mapped to the executor GachaCommand, allowing players to open the reward interface by typing the command in chat.

```java
public final class GachaPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Register events and GUI
        getServer().getPluginManager().registerEvents(new GachaGUI(null), this);

        // Register the /gacha command
        getCommand("gacha").setExecutor(new GachaCommand(this));

        getLogger().info("@ Gacha Plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("@ Gacha Plugin disabled.");
    }
}
````
### GachaGUI Class
The GachaGUI class handles the creation of the graphical interface, the drawing of rewards, and the animation of the items on the screen. When the player uses the /gacha command, this GUI opens and shows a random animation before a reward is revealed.

### GachaCommand Class
The GachaCommand class is responsible for managing the /gacha command, allowing players to open the reward interface and start the reward drawing process.

## Example Rewards
The GachaPlugin reward system is configured with items and their respective chances of being drawn. For example:

- Diamond Block (5% chance)
- Iron Block (10% chance)
- Coal Block (35% chance)
- Gold Block (20% chance)
- Lapis Lazuli Block (20% chance)
These values can be easily configured and adjusted in the code.

## Requirements
- Minecraft Server with support for Spigot or Paper.
- Java 17+ (or compatible version).
## How to Contribute
#### 1. Fork this repository.
#### 2. Create a branch for your feature (git checkout -b my-feature).
#### 3. Make your changes.
#### 4. Submit a Pull Request with a clear description of what you have done.
## License
This project is licensed under the MIT License - see the LICENSE file for details.

## About
This plugin was developed by JPyCode to demonstrate the implementation of interactive reward systems in Minecraft servers. If you have ideas for new features or improvements, feel free to contribute!
