#!/bin/bashh

# Varsa eski konteynerleri durdur ve temizle
sudo docker compose down || true

# En güncel imajı DockerHub'dan çek
sudo docker compose pull

# Uygulamayı arka planda başlat
sudo docker compose up -d

# Kullanılmayan eski imajları temizle (disk dolmaması için)
sudo docker image prune -f