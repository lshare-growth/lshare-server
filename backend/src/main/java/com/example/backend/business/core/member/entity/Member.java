package com.example.backend.business.core.member.entity;

import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.common.District;
import com.example.backend.business.core.common.values.DateTime;
import com.example.backend.business.core.common.values.ProfileImageUrl;
import com.example.backend.business.core.member.entity.values.BirthDate;
import com.example.backend.business.core.member.entity.values.BlogUrl;
import com.example.backend.business.core.member.entity.values.CurrentLoginIpAddress;
import com.example.backend.business.core.member.entity.values.FollowerCount;
import com.example.backend.business.core.member.entity.values.FollowingCount;
import com.example.backend.business.core.member.entity.values.Followings;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.GithubLink;
import com.example.backend.business.core.member.entity.values.Introduction;
import com.example.backend.business.core.member.entity.values.LastLoginIpAddress;
import com.example.backend.business.core.member.entity.values.LoginCount;
import com.example.backend.business.core.member.entity.values.LoginDate;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.core.member.entity.values.PhoneNumber;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private GithubId githubId;

    @Embedded
    private Followings followings;

    @Embedded
    private ProfileImageUrl profileImageUrl;

    @Embedded
    private NickName nickName;

    @Embedded
    private Introduction introduction;

    @Column(columnDefinition = "ENUM('SEOUL', 'BUSAN', 'DAEGU', 'INCHEON', 'DAEJEON', 'ULSAN')")
    @Enumerated(EnumType.STRING)
    private District district;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private BirthDate birthDate;

    @Embedded
    private BlogUrl blogUrl;

    @Embedded
    private LoginDate loginDate;

    @Embedded
    private LoginCount loginCount;

    @Embedded
    private GithubLink githubLink;

    @Column(columnDefinition = "ENUM('ADMIN', 'USER')")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Embedded
    private FollowerCount followerCount;

    @Embedded
    private FollowingCount followingCount;

    @Embedded
    private CurrentLoginIpAddress currentLoginIpAddress;

    @Embedded
    private LastLoginIpAddress lastLoginIpAddress;

    @Embedded
    private DateTime dateTime;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Member() {
    }

    public Member(String githubId, String profileImageUrl) {
        this.githubId = GithubId.from(githubId);
        this.nickName = NickName.from(githubId);
        this.introduction = Introduction.initIntroduction();
        this.profileImageUrl = ProfileImageUrl.from(profileImageUrl);
        this.role = MemberRole.initMemberRole();
        this.loginDate = LoginDate.initLoginDate();
        this.loginCount = LoginCount.initLoginCount();
        this.dateTime = DateTime.initDateTime();
        this.githubLink = GithubLink.from(this.githubId);
        this.blogUrl = BlogUrl.initBlogUrl();
        this.followerCount = FollowerCount.initFollowerCount();
        this.followingCount = FollowingCount.initFollowingCount();
        this.deleted = Deleted.initDeletion();
    }

    public MemberId getMemberIdAsValue() {
        return MemberId.from(memberId);
    }

    public Long getMemberId() {
        return memberId;
    }

    public GithubId getGithubIdAsValue() {
        return githubId;
    }

    public String getGithubId() {
        return githubId.getGithubId();
    }

    public String getGithubLink() {
        return githubLink.getGithubLink();
    }

    public NickName getNickNameAsValue() {
        return nickName;
    }

    public String getNickName() {
        return nickName.getNickName();
    }

    public Introduction getIntroductionAsValue() {
        return introduction;
    }

    public String getIntroduction() {
        return Objects.isNull(introduction) ? "" : introduction.getIntroduction();
    }

    public District getDistrict() {
        return district;
    }

    public BirthDate getBirthDateAsValue() {
        return birthDate;
    }

    public LocalDate getBirthDate() {
        return Objects.isNull(birthDate) ? null : birthDate.getBirthDate();
    }

    public String getProfileImageUrl() {
        return Objects.isNull(profileImageUrl) ? "" : profileImageUrl.getProfileImageUrl();
    }

    public BlogUrl getBlogUrlAsValue() {
        return blogUrl;
    }

    public String getBlogUrl() {
        return blogUrl.getBlogUrl();
    }

    public int getFollowerCount() {
        return followerCount.getFollowerCount();
    }

    public int getFollowingCount() {
        return followingCount.getFollowingCount();
    }

    public Deleted getDeleted() {
        return deleted;
    }

    public void updateProfile(BlogUrl blogUrl,
                              District district,
                              BirthDate birthDate,
                              Introduction introduction) {

        this.blogUrl = blogUrl.updateBlogUrl(this.blogUrl, blogUrl);
        this.district = district.updateDistrict(this.district, district);
        this.birthDate = birthDate.updateBirthDate(this.birthDate, birthDate);
        this.introduction = introduction.updateIntroduction(this.introduction, introduction);
        dateTime.update();
    }

    public void updateNickName(NickName nickName) {
        this.nickName = nickName;
    }

    public void follow(Follow follow) {
        this.followings.add(follow);
        increaseFollowingCount();
    }

    public void increaseFollowingCount() {
        this.followingCount = FollowingCount.from(followingCount.increaseAndGet());
    }

    public void decreaseFollowingCount() {
        this.followingCount = FollowingCount.from(followingCount.decreaseAndGet());
    }

    public void increaseFollowerCount() {
        this.followerCount = FollowerCount.from(followerCount.increaseAndGet());
    }

    public void decraseFollowerCount() {
        this.followerCount = FollowerCount.from(followerCount.decraseAndGet());
    }

    public void updateIpAddress(CurrentLoginIpAddress currentLoginIpAddress) {
        updateLastLoginIpAddress(currentLoginIpAddress);
        this.currentLoginIpAddress = currentLoginIpAddress;
    }

    private void updateLastLoginIpAddress(CurrentLoginIpAddress currentLoginIpAddress) {
        if (Objects.isNull(this.currentLoginIpAddress)) {
            this.lastLoginIpAddress = getUpdatedIpAddress(currentLoginIpAddress);
            return;
        }
        this.lastLoginIpAddress = getUpdatedIpAddress(this.currentLoginIpAddress);
    }

    private LastLoginIpAddress getUpdatedIpAddress(CurrentLoginIpAddress currentLoginIpAddress) {
        return lastLoginIpAddress.updateIpAddress(currentLoginIpAddress.getCurrentLoginIpAddress());
    }

    public void delete() {
        this.deleted = Deleted.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return getMemberId().equals(member.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Override
    public String toString() {
        return memberId.toString();
    }
}
