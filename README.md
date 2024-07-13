
# AuraLevelledExp

[![Release](https://img.shields.io/badge/release-0.2-blue?logo=github)](https://github.com/thrddqno/AuraLevelledExp/releases/tag/latest)

An add-on for LevelledMobs and AuraSkills based on AuraMobs!

## Usage
### Commands
Aliases

`/auralevelledmobs`, `/alexp`, `/ale`, `/lxp`

Subcommands

`/auralevelledmobs reload`

### Config

The config is simple and concise as the add-on is. We can disable the plugin by using the `enabled: true` configuration option.

The `growth_rate` option determines how rapidly the experience points should increase with each level. It is part of the XP Modifier Formula

$$
\text{XP} = \text{Source XP} \times \left(1 + \frac{\text{Mob Level}}{100}\right)^{\text{Growth Rate}}
$$

## Credits
[ArchyX](https://github.com/Archy-X)
- [AuraMobs](https://github.com/Archy-X/AuraMobs/tree/5f3a20379984265d7a7fbc7b1ac1715d76a2e34d)

[megaphag](https://www.spigotmc.org/members/megaphag.933033/) (ConfigManager)
