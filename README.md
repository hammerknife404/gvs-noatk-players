# Guard FF Patch

NeoForge 1.21.1 server-side patch for [Guard Villagers](https://www.curseforge.com/minecraft/mc-mods/guard-villagers): guard projectiles no longer damage players unless the guard is actually targeting that player.

## Build

```
./gradlew build
```

Jar lands in `build/libs/`. CI builds every push to `main` and every pull request and uploads the jar as a workflow artifact. Builds are versioned `X.Y.Z-dev` locally and `X.Y.Z-dev.<commit>` in CI; only the Release workflow builds a plain `X.Y.Z` jar.

To release, bump `mod_version` in `gradle.properties` and merge to `main`: the Release workflow publishes a GitHub release tagged `v<version>` with the jar. Pushes that don't change the version don't release.

## Behavior

- Only cancels damage to **players** from **projectiles** shot by `guardvillagers:guard`.
- Shots at a player the guard is targeting (bad reputation, hit a villager) still land.
- Server-side only; clients don't need it. No hard dependency on Guard Villagers.
