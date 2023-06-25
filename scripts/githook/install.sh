#!/usr/bin/env sh

SCRIPT_DIR=$(dirname "$0")
ln -sf "../../$SCRIPT_DIR/pre-commit" .git/hooks/pre-commit
