package com.app.springapp.config;

import com.app.springapp.domain.dto.request.EduWordFromSignWordRequestDTO;
import com.app.springapp.exception.EduException;
import com.app.springapp.repository.SignWordDAO;
import com.app.springapp.service.edu.EduWordMapService;
import com.app.springapp.service.edu.SignWordService;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyDataBootstrapRunner implements ApplicationRunner {
    private final SignWordDAO signWordDAO;
    private final SignWordService signWordService;
    private final EduWordMapService eduWordMapService;

    @Value("${study.bootstrap.enabled:false}")
    private boolean bootstrapEnabled;

    // OpenAPI 자동 동기화
    @Override
    public void run(ApplicationArguments args) {
        if (!bootstrapEnabled) {
            return;
        }

        int signWordCount = signWordDAO.countAll();
        System.out.println("study bootstrap enabled = " + bootstrapEnabled);
        System.out.println("sign word count before sync = " + signWordCount);

        if (signWordCount == 0) {
            int totalSavedCount = 0;
            for (int pageNo = 1; pageNo <= 3; pageNo++) {
                totalSavedCount += signWordService.syncSignWords(pageNo, 100);
            }

            int afterSyncCount = signWordDAO.countAll();

            System.out.println("sign word saved count = " + totalSavedCount);
            System.out.println("sign word count after sync = " + afterSyncCount);

            if (afterSyncCount == 0) {
                System.out.println("study bootstrap stopped: OpenAPI sync result is empty.");
                return;
            }
        }
        bootstrapEduWords();
    }

    // 기본 학습 단어 등록
    private void bootstrapEduWords() {
        registerEduWord(1L, 4L, "기초");
        registerEduWord(1L, 5L, "기초");
        registerEduWord(1L, 21L, "기초");
        registerEduWord(1L, 39L, "기초");
        registerEduWord(1L, 52L, "기초");

        registerEduWord(2L, 68L, "중급");
        registerEduWord(2L, 69L, "중급");
        registerEduWord(2L, 70L, "중급");
        registerEduWord(2L, 84L, "중급");
        registerEduWord(2L, 90L, "중급");

        registerEduWord(3L, 111L, "고급");
        registerEduWord(3L, 112L, "고급");
        registerEduWord(3L, 113L, "고급");
        registerEduWord(3L, 195L, "고급");
        registerEduWord(3L, 197L, "고급");
    }

    // 하나씩 등록
    private void registerEduWord(Long eduId, Long signWordId, String wordsType) {
        EduWordFromSignWordRequestDTO requestDTO = new EduWordFromSignWordRequestDTO();
        requestDTO.setEduId(eduId);
        requestDTO.setSignWordId(signWordId);
        requestDTO.setWordsType(wordsType);

        try {
            eduWordMapService.saveEduWordFromSignWord(requestDTO);
        } catch (EduException e) {
            if (e.getHttpStatus() == HttpStatus.CONFLICT) {
                return;
            }
            throw e;
        }
    }
}
