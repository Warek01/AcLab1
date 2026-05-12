#!/bin/bash

DOCKER_SOCK="${HOME}/.colima/default/docker.sock"

docker container rm -f gh-runner || true

docker run -d \
  --platform linux/amd64 \
  --name gh-runner \
  --privileged \
  gh-runner
