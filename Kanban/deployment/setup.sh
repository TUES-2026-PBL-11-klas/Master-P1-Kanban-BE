set -e

CURRENT_USER=$(whoami)
CURRENT_DIR=$(pwd)

echo "$CURRENT_USER to be used for configuration of systemd service in $CURRENT_DIR. Deploying service."

sed -e "s|{{USER}}|$CURRENT_USER|g" \
    -e "s|{{WORKDIR}}|$CURRENT_DIR|g" \
    kanban.service.template > kanban.service

sudo mv kanban.service /etc/systemd/system/kanban.service
sudo systemctl daemon-reload
sudo systemctl enable kanban.service
sudo systemctl start kanban.service --now

echo "Checking for .env file in $CURRENT_DIR"
if [ ! -f .env ]; then
    echo "Error: .env file not found! Copy .env.example and fill it in."
    exit 1
fi

echo "Starting Database container"
docker-compose -f docker-compose.yml up -d

echo "Building Application"
cd ..
./mvnw clean package -DskipTests

echo "Deployment complete."
