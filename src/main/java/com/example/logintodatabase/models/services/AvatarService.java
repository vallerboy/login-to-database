package com.example.logintodatabase.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class AvatarService {

    final ContactService contactService;
    private static final String PATH_TO_AVATARS = "avatars";

    @Autowired
    public AvatarService(ContactService contactService) {
        this.contactService = contactService;
    }

    public boolean uploadAvatar(MultipartFile avatar, int contactId){
        if(!isFileCorrect(avatar) && !contactService.isLoginUserOwnerOfContactWithId(contactId)){
            return false;
        }

        try {
            copyAvatarToResourceFolder(avatar, contactId);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean deleteAvatar(int contactId){
         if(!contactService.isLoginUserOwnerOfContactWithId(contactId)){
             return false;
         }

        try {
            deleteAvatarResource(contactId);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private void deleteAvatarResource(int contactId) throws IOException {
        Files.delete(getPath(contactId));
    }

    private void copyAvatarToResourceFolder(MultipartFile avatar, int contactId) throws IOException {
        Path pathToAvatar = getPath(contactId);
        createFileIfNotExist(pathToAvatar);

        Files.write(pathToAvatar, avatar.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    private Path getPath(int contactId) {
        return Paths.get(PATH_TO_AVATARS + File.separator + contactId);
    }

    private void createFileIfNotExist(Path pathToAvatar) throws IOException {
        File file = pathToAvatar.toFile();
        if(!file.exists()){
            file.createNewFile();
        }
    }

    private boolean isFileCorrect(MultipartFile avatar) {
         return checkFileFormat(avatar) && checkFileSize(avatar);
    }

    private boolean checkFileSize(MultipartFile avatar) {
        return avatar.getSize() <= 1024 * 1024 * 5;
    }

    private boolean checkFileFormat(MultipartFile avatar) {
        return avatar.getContentType() != null && (avatar.getContentType().contains("png") || avatar.getContentType().contains("image"));
    }


}

