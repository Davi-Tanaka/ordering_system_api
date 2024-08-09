aws --endpoint-url=http://localstack:4566 \
  s3api create-bucket --bucket ${S3_USER_IMAGE_BUCKET} --region ${S3_REGION}
aws --endpoint-url=http://localstack:4566 \
  s3api create-bucket --bucket ${S3_PRODUCT_IMAGE_BUCKET} --region ${S3_REGION}
