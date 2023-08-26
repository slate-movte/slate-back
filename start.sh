cd docker_compose_local
docker compose --env-file ../config/.env.yml down
docker compose --env-file ../config/.env.yml build --no-cache
docker compose --env-file ../config/.env.yml up -d