#!/bin/bash

# If there are old containers, stop and clean them.
sudo docker compose down || true

# Pull the latest image from DockerHub.
sudo docker compose pull

# Start the app in the background.
sudo docker compose up -d

# Clean unused old images (to prevent the disk from filling up).
sudo docker image prune -f