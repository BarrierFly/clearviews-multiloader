# Clearviews

## This fork vs. the original repository

This repository is a fork of [michiel1106/clearviews-multiloader](https://github.com/michiel1106/clearviews-multiloader), which is itself the upstream
home of Clearviews. At the moment it differs from that upstream in the following ways:

- **Only Minecraft 1.21.11 on Fabric is enabled.** Every other Minecraft version / loader combination that upstream builds
  (1.21.1 – 1.21.10, 1.21.11 NeoForge and 26.1.2) is temporarily disabled in `settings.gradle.kts` so that only
  `1.21.11-fabric` is configured, built and published. The other `versions/*` folders are kept in the tree, so re-enabling
  them is just a matter of uncommenting the relevant `mc(...)` lines.
- **New feature: nether portal progress bar.** A horizontal progress bar with a percentage readout is drawn at the bottom
  centre of the screen while you are travelling through a nether portal, in place of the vanilla full-screen portal
  progress indication. It is controlled by the new *Nether Portal Progress Bar* option (on by default) and the
  *Nether Portal Progress Duration* option (80 ticks by default).
- The existing `Disable Portal Overlay` option is left untouched and works independently of the new progress bar, so the
  vanilla purple portal overlay and the new progress bar can be combined or separated freely.

Upstream is credited for all pre-existing functionality — everything related to nausea, blindness, darkness, fog, overlays,
particles, fire and boss bars is unchanged from upstream.

---

Clearviews is a mod that adds a bunch of options for disabling certain annoying ingame effects.
