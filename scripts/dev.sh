#!/bin/bash

set -euo pipefail

COMPOSE_FILE=compose.yml
PROJECT_NAME=ac-lab
TIMEOUT=10

if [ -f "${COMPOSE_FILE}" ]; then
  echo "Found ${COMPOSE_FILE} in current directory."
elif [ -f "../${COMPOSE_FILE}" ]; then
  echo "Found ${COMPOSE_FILE} in parent directory."
  cd ..
else
  echo "${COMPOSE_FILE} not found. Aborting ..."
  exit 1
fi

echo "Stopping existing containers..."
docker compose -p ${PROJECT_NAME} -f compose.yml down -t ${TIMEOUT}

echo "Starting containers..."
docker compose -p ${PROJECT_NAME} --env-file .env -f compose.yml \
  up -d --build -t ${TIMEOUT} --wait --pull missing
