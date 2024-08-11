#!/bin/bash

LOCALSTACK_URL=http://localstack:4566

if [ aws --endpoint-url=$LOCALSTACK_URL s3api head-bucket --bucket $S3_USER_IMAGE_BUCKET 2>/dev/null ]; then
    echo $S3_USER_IMAGE_BUCKET already exist.
else
    aws --endpoint-url=$LOCALSTACK_URL \
        s3api create-bucket --bucket $S3_USER_IMAGE_BUCKET --region ${S3_REGION}
fi

if [ aws --endpoint-url=$LOCALSTACK_URL s3api head-bucket --bucket $S3_PRODUCT_IMAGE_BUCKET 2>/dev/null ]; then  
     echo $S3_PRODUCT_IMAGE_BUCKET already exist.
else 
    aws --endpoint-url=$LOCALSTACK_URL \
        s3api create-bucket --bucket $S3_PRODUCT_IMAGE_BUCKET --region $S3_REGION
fi