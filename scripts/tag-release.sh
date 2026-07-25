#!/bin/bash
set -eu

VERSION_FILE="VERSION"

if [ ! -f "$VERSION_FILE" ]; then
  echo "Error:: file '$VERSION_FILE' not found!"
  exit 1
fi

VERSION=$(cat "$VERSION_FILE" | tr -d '[:space:]')

if [ -z "$VERSION" ]; then
  echo "Error:: version is empty.!"
  exit 1
fi

TAG_NAME="v$VERSION"

if git rev-parse "$TAG_NAME" >/dev/null 2>&1; then
  echo "Tag '$TAG_NAME' already exists."
  exit 0
fi

git config user.name "github-actions"
git config user.email "github-actions@users.noreply.github.com"

git tag -a "$TAG_NAME" -m "Release $VERSION"
git push origin "$TAG_NAME"