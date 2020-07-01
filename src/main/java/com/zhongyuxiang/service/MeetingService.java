package com.zhongyuxiang.service;

import com.zhongyuxiang.entity.Meeting;
import com.zhongyuxiang.dao.MeetingDao;
import com.zhongyuxiang.entity.Page2;
import com.zhongyuxiang.enums.StatusEnum;
import com.zhongyuxiang.utils.DateUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @auth zyx
 * @date 2020/6/30
 * @Description
 */
public class MeetingService {

    private MeetingDao meetingDao = new MeetingDao();

    public List<Meeting> listPage(Meeting meeting, Page2 page) {
        return meetingDao.listPage(meeting, page);
    }

    public Integer count(Meeting meeting) {
        return meetingDao.count(meeting);
    }

    public void addMeeting(Meeting meeting) {
        meeting.setMakeUser(Arrays.toString(meeting.getMakeUsers()));
        meeting.setStatus(0);
        meeting.setPublishDate(DateUtil.getDateStr());
        meeting.setCreateTime(DateUtil.getDateStr());
        meetingDao.addMeeting(meeting);
    }

    public Meeting getMeetingById(Integer id) {
        return meetingDao.getMeetingById(id);
    }

    public Integer getMeetingCountByMeetingId(Integer meetingId) {
        return meetingDao.getMeetingCountByMeetingId(meetingId);
    }

    public Integer checkJoinMeeting(Integer userId, Integer meetingId) {
        //已经参加会议
        if (meetingDao.checkJoinMeeting(userId, meetingId) > 0) {
            return 1;
        } else {
            //未参加会议
            return 2;
        }
    }

    public void joinMeeting(Integer userId, Integer meetingId) {
        meetingDao.joinMeeting(userId, meetingId);
    }

    public void unJoinMeeting(Integer userId, Integer meetingId) {
        meetingDao.unJoinMeeting(userId, meetingId);
    }

    public void updateStatusTask() {
        List<Meeting> list = meetingDao.listAll();
        for (Meeting meeting : list) {
            //当前时间戳
            long currentTime = new Date().getTime();
            long startTime = DateUtil.getTimeByStr(meeting.getStartTime());
            long endTime = DateUtil.getTimeByStr(meeting.getEndTime());

            if (startTime <= currentTime) {
                if (endTime > currentTime) {
                    //会议正在进行中
                    meetingDao.updateStatus(meeting.getId(), StatusEnum.DOING.getValue());
                } else {
                    //会议已经结束
                    meetingDao.updateStatus(meeting.getId(), StatusEnum.END.getValue());
                }
            } else {
                //会议未开始，不需要处理
            }

        }
    }

}
