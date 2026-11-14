# Guard FF Patch

NeoForge 1.21.1 server-side patch for [Guard Villagers](https://www.curseforge.com/minecraft/mc-mods/guard-villagers): guard projectiles no longer damage players unless the guard is actually targeting that player.

## Build

```
./gradlew build
```

Jar lands in `build/libs/`. CI builds on every push and uploads the jar as a workflow artifact.

## Behavior

- Projectiles shot by `guardvillagers:guard` pass through **players** (no damage, no bounce) and keep flying toward the real target.
- Shots at a player the guard is targeting (bad reputation, hit a villager) still land.
- Server-side only; clients don't need it. No hard dependency on Guard Villagers.
