# Contributing

See [AGENTS.md](AGENTS.md) for the rules of a recipe. Use Conventional Commits; `CI gate` must be green.

## Adding a recipe

1. Create `recipes/<engine>-<problem>/` with its `recipe.yaml`, `README.md` and `AGENTS.md`.
2. Add its row to the root `README.md`.
3. Add its isolated blocks to `.github/dependabot.yml`, labelled `recipe:<name>`.
4. Add yourself to `.github/CODEOWNERS` as owner of the recipe's path only, for example `/recipes/<name>/ @you`. The recipe is yours to review and keep up to date.
