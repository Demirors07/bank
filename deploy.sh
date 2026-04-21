#!/bin/bash

# Varsa eski konteynerleri durdur ve temizle
docker compose down || true

# En güncel imajı DockerHub'dan çek
docker compose pull

# Uygulamayı arka planda başlat
docker compose up -d

# Kullanılmayan eski imajları temizle (disk dolmaması için)
docker image prune -f