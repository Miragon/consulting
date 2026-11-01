# process-engine-recipes

[![CI](https://github.com/Miragon/consulting/actions/workflows/build.yaml/badge.svg?branch=main)](https://github.com/Miragon/consulting/actions/workflows/build.yaml) [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

One concrete process-engine problem per folder, solved in working code. Find your problem, read how it was solved and take what you need.

## Why

Most engine material shows how to use an engine. The harder questions come later: how do I put single sign-on in front of Cockpit, or run my own tasklist on Zeebe? [process-engine-blueprints](https://github.com/Miragon/process-engine-blueprints) shows whole services that automate a process. A recipe here shows one solution:

- **One problem.** A recipe contains the code for its problem and nothing else.
- **As it is.** Each recipe exists in one language. There are no variants and no starter: copy the folder or the part you need.
- **Self-contained.** Every recipe builds on its own, and nothing is shared between recipes.
- **Checked by CI.** Each recipe is built on every change that touches it and names the engine version it was built for.

## Recipes

| Problem | Recipe | Engine | Language |
| --- | --- | --- | --- |
| Single sign-on for the web applications | [`camunda7-cockpit-sso`](recipes/camunda7-cockpit-sso/README.md) | Camunda 7.24 | Java |
| Your own tasklist via a Zeebe exporter | [`zeebe-external-tasklist`](recipes/zeebe-external-tasklist/README.md) | Camunda 8.5 | Kotlin |

Looking for an older showcase? They are preserved at the tag [`archive/showcases-2026`](https://github.com/Miragon/consulting/tree/archive/showcases-2026).

## Working on a recipe

Open the recipe's folder, not the repository root. Its `README.md` and `AGENTS.md` treat that folder as the project.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md). The decisions behind this repository are in [docs/adr](docs/adr/).

## License

Distributed under the MIT License. See `LICENSE` file for more information.
