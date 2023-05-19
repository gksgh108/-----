package com.tukorea.mypage.service;

import com.tukorea.mypage.dao.MypageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@Service
public class MypageService {

    @Value("${file.dir.save}")
    private String saveDir; // 실제 저장 디렉토리

    @Value("${file.dir.view}")
    private String viewDir; // 보여지는 저장 디렉토리
    private MypageDao dao;

    @Autowired
    public MypageService(MypageDao dao) {
        this.dao = dao;
    }

    public HashMap<String, Object> getMemberDetail(int memberSeq) {
        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            // [1] 회원 상세 정보 조회
            resultMap = dao.selectMemberinfo(memberSeq);

            String db_save_path = resultMap.get("PROFILE_IMG_SAVE_PATH").toString(); // a.png
            resultMap.put("PROFILE_IMG_SAVE_PATH", viewDir + db_save_path); // upload/a.png

            System.out.println("[1] 회원 상세 정보 조회 완료");

        } catch (Exception e){
            e.printStackTrace();

        }
        return resultMap;
    }

    public HashMap<String, Object> modifyProfileImage(HashMap<String, Object> paramMap) {
        HashMap<String, Object> resultMap = new HashMap<>();

        String result_cd = "00";
        String result_msg = "정상";

        try {
            // DB 저장을 위한 파라미터 설정
            MultipartFile file = (MultipartFile) paramMap.get("file");


            // [1] 원본 파일명
            String originName = file.getOriginalFilename();
            paramMap.put("imageName", originName);

            // [2] 실제 파일 저장 경로
            // 저장 파일명으로 쓸 UUID 생성(랜덤)
            String uuid = UUID.randomUUID().toString();

            // 파일 확장자 추출
            String fileExt = originName.substring(originName.lastIndexOf(".")); // .이후를 반환

            // 저장할 파일명
            String saveFileName = uuid + fileExt;
            paramMap.put("savePath", saveFileName);

            // 프로필 이미지 정보 DB 저장(기존 테이블이 있기에 update)
            int resultCount = dao.updateProfileImage(paramMap);

            if (resultCount != 1) {
                throw new Exception("회원 프로필 이미지 수정에 실패했습니다.");
            }

            // 프로필 이미지를 서버에 물리 저장
            String serverSavePath = saveDir + saveFileName; // 실제 저장 Dir + 파일명
            File serverSaveFile = new File(serverSavePath);

            file.transferTo(serverSaveFile);

        } catch (Exception e) {
            result_cd = "99";
            result_msg = e.getMessage();

            e.printStackTrace();

        } finally {
            resultMap.put("result_cd", result_cd);
            resultMap.put("result_msg", result_msg);
        }

        return resultMap;
    }
}
