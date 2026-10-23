# Guard FF Patch

NeoForge 1.21.1 server-side patch for [Guard Villagers](https://www.curseforge.com/minecraft/mc-mods/guard-villagers): guard projectiles no longer damage players unless the guard is actually targeting that player.

## Build

```
./gradlew build
```

Jar lands in `build/libs/`. CI builds on every push and uploads the jar as a workflow artifact.

## Behavior

- Only cancels damage to **players** from **projectiles** shot by `guardvillagers:guard`.
- Shots at a player the guard is targeting (bad reputation, hit a villager) still land.
- Server-side only; clients don't need it. No hard dependency on Guard Villagers.
