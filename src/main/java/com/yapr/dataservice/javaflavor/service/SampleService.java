package com.yapr.dataservice.javaflavor.service;


import com.yapr.dataservice.javaflavor.model.MediaItem;
import com.yapr.dataservice.javaflavor.persistence.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Service
public class SampleService {
    private MediaRepository mediaRepository;

    @Autowired
    SampleService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostConstruct
    public void postConstruction() throws Exception {
        int abc = 123;
        MediaItem mediaItem = new MediaItem();
        mediaItem.setCaption("Sample item " + System.currentTimeMillis());
//        mediaItem.setRecordedTime(ZonedDateTime.now());
//        mediaItem.setUploadTime(ZonedDateTime.now());
        MediaItem found = this.mediaRepository.save(mediaItem);

//        uploadFile2();
        abc = 123;


    }

    public Collection<String> getDataCollectionNames() {
        return this.mediaRepository.getDataCollectionNames();
    }

    public void uploadFile1() {
        String REGION = "us-east-1";
        String SERVICE_ENDPOINT = "https://s3.wasabisys.com";
        String ACCESS_KEY = "";
        String SECRET_KEY = "";
        String BUCKET_NAME = "dev-videos-01";

        S3Client client1 = S3Client.builder()
                .endpointOverride(URI.create(SERVICE_ENDPOINT))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .build();

        client1.putObject(
                PutObjectRequest.builder()
                        .bucket(BUCKET_NAME)
                        .key("2015/10/19/Blue Ridge Parkway/IMG_2466.MOV")
                        .build(),
                Paths.get("D:\\OneDrive-nguyen10820\\OneDrive\\OneDrive - nguyen10820 - gmail.com\\Pictures\\Hình ảnh\\2015-10-19 Blue Ridge Parkway\\IMG_2466.MOV"));

        int abc = 123;
    }

    public void uploadFile2() throws Exception {
        String REGION = "us-east-1";
        String SERVICE_ENDPOINT = "https://s3.wasabisys.com";
        String ACCESS_KEY = "";
        String SECRET_KEY = "";
        String BUCKET_NAME = "dev-videos-01";

        S3AsyncClient client2 = S3AsyncClient.builder()
                .endpointOverride(URI.create(SERVICE_ENDPOINT))
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .build();


//        client2.putObject(PutObjectRequest.builder()
//                        .bucket(BUCKET_NAME)
//                        .key("20190730_201135000_iOS.MOV")
//                        .build(),
//                Paths.get("D:\\OneDrive\\Pictures\\2019-07-17 Summer\\20190730_201135000_iOS.MOV")).get();

        client2.listBuckets().get().buckets().stream().forEach(b -> {
            System.out.println("Bucket name " + b.name());
        });
        S3TransferManager transferManager =
                S3TransferManager.builder()
                        .s3Client(client2)
                        .build();


        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(req -> req.bucket("dev-videos-01").key("2019/07/17/Summer/20190721_213502.jpg"))
                .addTransferListener(LoggingTransferListener.create())
                .source(Paths.get("D:\\OneDrive\\Pictures\\2019-07-17 Summer\\20190721_213502.jpg"))
                .build();

        FileUpload upload = transferManager.uploadFile(uploadFileRequest);
        upload.completionFuture().thenApply(a -> {
            System.out.println(a.response().versionId());
            return CompletableFuture.completedFuture(true);
        });

        int abc = 123;
    }
}
