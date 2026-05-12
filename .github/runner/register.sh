#!/bin/bash

RUNNER_REPO=${RUNNER_REPO:-"https://github.com/Warek01/AcLab1"}

docker build \
  --build-arg RUNNER_TOKEN=${RUNNER_TOKEN} \
  --build-arg RUNNER_REPO=${RUNNER_REPO} \
  --platform linux/amd64 \
  --file gh-runner.Dockerfile \
  -t gh-runner \
  .
