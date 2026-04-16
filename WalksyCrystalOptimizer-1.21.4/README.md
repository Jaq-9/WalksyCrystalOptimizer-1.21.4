# Walksy Crystal Optimizer — 1.21.4 Port

Allows for faster crystal placement in Minecraft 1.21.4 (Fabric).

## Requirements

- Minecraft **1.21.4**
- [Fabric Loader](https://fabricmc.net/use/installer/) `>=0.16.9`
- [Fabric API](https://modrinth.com/mod/fabric-api) for 1.21.4
- Java **21**

## Usage

Toggle the optimizer in-game with:
```
/crystaloptimizer
```
Green = **Enabled**, Red = **Disabled**.

> ⚠️ Do **not** use alongside Anti Crystal Refresh — it causes crystals to deplete too fast.

## Building from Source

### Prerequisites
- Java 21 JDK
- Git

### Steps

```bash
git clone <this-repo>
cd WalksyCrystalOptimizer-1.21.4

# On Linux/macOS:
./gradlew build

# On Windows:
gradlew.bat build
```

The compiled `.jar` will be in `build/libs/`.  
Drop it into your `.minecraft/mods/` folder alongside Fabric API.

## Changes from original (1.19.x → 1.21.4)

| Change | Detail |
|---|---|
| `fabric-loom` | Bumped from `0.12` to `1.9` |
| Java target | 17 → 21 |
| `loader_version` | Updated to `0.16.9` |
| `yarn_mappings` | Updated to `1.21.4+build.8` |
| `fabric_version` | Updated to `0.119.5+1.21.4` |
| Client commands | Migrated to `fabric-api` v2 client command API (`FabricClientCommandSource`) |
| `jcenter()` removed | Replaced with standard Gradle Plugin Portal in `settings.gradle` |
| `compatibilityLevel` | Mixin JSON updated to `JAVA_21` |

## Credits

Original mod by [Walksy](https://github.com/Walksy/WalksyCrystalOptimizer) — MIT License.
