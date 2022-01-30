package zangzangs.slf4jlogbacktraining.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zangzangs.slf4jlogbacktraining.domain.Member;
import zangzangs.slf4jlogbacktraining.domain.Team;
import zangzangs.slf4jlogbacktraining.repository.MemberRepository;
import zangzangs.slf4jlogbacktraining.repository.TeamRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamServiceTest {
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member member;
    private Team team;

    @BeforeEach
    void init() {
        member = new Member("성실한 직원", 27);
        team = new Team("좋은 팀");
        memberRepository.save(member);
        teamRepository.save(team);
    }

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @DisplayName("멤버 추가 - 성공")
    @Test
    @Transactional
    void addMember() {
        //given
        //when
        teamService.addMember("좋은 팀", "성실한 직원");
        Team findTeam = teamRepository.findByName("좋은 팀").get();
        //then
        assertThat(findTeam.getMembers()).contains(member);
    }
}