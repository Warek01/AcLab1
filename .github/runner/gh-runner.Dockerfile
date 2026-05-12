FROM ubuntu:22.04

ARG RUNNER_TOKEN
ARG RUNNER_REPO

RUN apt update && apt install -y \
    curl sudo git jq \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -m runner && echo "runner ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER runner
WORKDIR /home/runner

RUN curl -o actions-runner-linux-x64-2.334.0.tar.gz \
    -L https://github.com/actions/runner/releases/download/v2.334.0/actions-runner-linux-x64-2.334.0.tar.gz

RUN echo "048024cd2c848eb6f14d5646d56c13a4def2ae7ee3ad12122bee960c56f3d271  actions-runner-linux-x64-2.334.0.tar.gz" | shasum -a 256 -c

RUN tar xzf ./actions-runner-linux-x64-2.334.0.tar.gz

RUN sudo ./bin/installdependencies.sh

RUN ./config.sh --url ${RUNNER_REPO} --token ${RUNNER_TOKEN}

ENTRYPOINT ["./run.sh"]