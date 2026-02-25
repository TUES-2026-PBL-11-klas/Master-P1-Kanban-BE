
echo "Step 1: Checking for .env file..."
if [ ! -f .env ]; then
    echo "Error: .env file not found! Copy .env.example and fill it in."
    exit 1
fi

echo "Step 2: Starting Database..."
docker-compose -f docker-compose.yml up -d

echo "Step 3: Building Application..."
cd ..
./mvnw clean package -DskipTests

echo "Step 4: Deployment complete."
