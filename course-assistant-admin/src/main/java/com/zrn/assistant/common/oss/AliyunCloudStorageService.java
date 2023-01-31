

package com.zrn.assistant.common.oss;

import com.aliyun.oss.OSSClient;
import com.zrn.assistant.common.exception.BusinessException;
import com.zrn.assistant.common.exception.ErrorCode;
import com.zrn.assistant.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;


public class AliyunCloudStorageService  {
    private static final String aliyunDomain = "https://shijin-video.oss-cn-chengdu.aliyuncs.com";

    private static final String aliyunPrefix = "course";

    private static final String aliyunEndPoint = "oss-cn-chengdu.aliyuncs.com";

    private static final String aliyunAccessKeyId = "LTAI5t8ia1mfYrp1wwqTZkB4";

    private static final String aliyunAccessKeySecret = "lLWgzwrzIsl1bRsBTaQNlhZZ65UPSM";


    private static final String aliyunBucketName = "shijin-video";


    public static String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }


    public static String upload(InputStream inputStream, String path) {
        OSSClient client = new OSSClient(aliyunEndPoint, aliyunAccessKeyId,
                aliyunAccessKeySecret);
        try {
            client.putObject(aliyunBucketName, path, inputStream);
            client.shutdown();
        } catch (Exception e){
            throw new BusinessException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }

        return aliyunDomain + "/" + path;
    }


    public static String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(aliyunPrefix, suffix));
    }


    public static String  uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(aliyunPrefix, suffix));
    }

    public static String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path + "." + suffix;
    }
}