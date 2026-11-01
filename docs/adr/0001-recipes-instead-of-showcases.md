# 0001 — Recipes instead of showcases

- **Status:** Accepted
- **Date:** 2026-11-01

## Context

This repository collected showcases, mostly for Miranum, while `process-engine-blueprints` now covers how to build whole services on an engine. What was missing is one place for solutions to concrete engine problems.

## Decision

Each example is a self-contained recipe for one problem under `recipes/`, in one language. CI builds changed recipes from their `recipe.yaml`. The blueprints' generator, drift checks, language variants and starter are not adopted, because recipes are not meant to be comparable.

## Consequences

Recipes are small and independent, and the catalog is maintained by hand. Removed showcases remain at the tag `archive/showcases-2026`.
