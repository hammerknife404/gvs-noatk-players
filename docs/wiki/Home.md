# Guard FF Patch

A NeoForge 1.21.1 server-side patch for [Guard Villagers](https://www.curseforge.com/minecraft/mc-mods/guard-villagers): guard projectiles no longer damage players unless the guard is actually targeting that player.

## Behavior

- Only cancels damage to **players** from **projectiles** shot by `guardvillagers:guard`.
- Shots at a player the guard is targeting (bad reputation, hit a villager) still land.
- Nothing else changes: guards still hurt mobs, and melee hits are untouched.

There's no config.

## Installation

Put the jar from [GitHub Releases](https://github.com/hammerknife404/gvs-noatk-players/releases) in the server's `mods/` folder, or your client's for singleplayer. Clients don't need it. Requires Minecraft 1.21.1 and NeoForge 21.1; it has no hard dependency on Guard Villagers and does nothing without it.

## Building and releasing

```
./gradlew build
```

The jar lands in `build/libs/`. CI builds every push to `main` and every pull request and uploads the jar as a workflow artifact. Builds are versioned `X.Y.Z-dev` locally and `X.Y.Z-dev.<commit>` in CI; only the Release workflow builds a plain `X.Y.Z` jar.

To release, bump `mod_version` in `gradle.properties` and merge to `main`: the Release workflow publishes a GitHub release tagged `v<version>` with the jar. Pushes that don't change the version don't release.

This page lives in `docs/wiki/` in the repository and is copied to the wiki on every push to `main`. Edit it there: changes made on github.com are overwritten by the next sync.
