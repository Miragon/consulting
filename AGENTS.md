# AGENTS.md

Each folder in `recipes/` is a self-contained project: open that folder and follow its `AGENTS.md`. Nothing is shared between recipes.

A recipe solves one problem, builds on its own, has an automated proof and states the tested engine version. It is named `<engine>-<problem>` and contains `recipe.yaml` (`name`, `problem`, `engine`, `language`, `java`, `build`), `README.md`, `AGENTS.md` and optionally `e2e.sh`.

Adding a recipe also means adding its row to the root `README.md` and its entries to `.github/dependabot.yml`. Use Conventional Commits, scoped to the recipe.
