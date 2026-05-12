#!/bin/bash
set -e

# Start Docker daemon in background
sudo dockerd &

# Wait for it to be ready
timeout 30 sh -c 'until docker info >/dev/null 2>&1; do sleep 1; done'

# Start the runner
./run.sh
