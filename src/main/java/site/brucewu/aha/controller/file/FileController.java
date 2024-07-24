package site.brucewu.aha.controller.file;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/aha/external/file")
@CrossOrigin("*")
@Slf4j
public class FileController {

    private final Path imageStoragePath = Paths.get("C:\\Users\\brucewu\\Desktop\\");
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        //文件夹路径,这里以时间作为目录
        String path = "C:\\Users\\brucewu\\Desktop\\" + format;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }


        multipartFile.transferTo(new File(file, originalFilename));

        String url = format + "/" + originalFilename;
        return url;
    }

    @GetMapping("/img/{date}/{fileName}")
    public void download(@PathVariable("date")String date, @PathVariable("fileName")String fileName, HttpServletRequest request, HttpServletResponse response) {

        String filename = date + "/" + fileName;
        try {
            Path file = imageStoragePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            log.debug(resource.getURL().getFile());
            if (resource.exists() || resource.isReadable()) {
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                resource.getInputStream();
                response.getOutputStream().write(resource.getInputStream().readAllBytes());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
