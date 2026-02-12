INSERT INTO alarm (member_id, alarm_time, label, is_enabled, sound_type, vibration, volume)
VALUES
    (2, '07:00:00', '출근 알람', TRUE, 'GENTLE_CHIME', TRUE, 70),
    (2, '08:30:00', '공부 시작', TRUE, 'DEFAULT_BELL', TRUE, 80),
    (2, '12:00:00', '점심시간', TRUE, 'SOFT_RING', FALSE, 60),
    (2, '18:00:00', '운동 알람', TRUE, 'POWER_RING', TRUE, 90),
    (2, '23:30:00', '취침 준비', TRUE, 'CALM_NIGHT', TRUE, 40),
    (2, '06:30:00', '아침 산책', TRUE, 'MORNING_WIND', TRUE, 65),
    (2, '14:00:00', '카페인 타임', TRUE, 'SHORT_BEEP', FALSE, 50),
    (2, '21:00:00', '독서 시간', TRUE, 'CLASSIC_TONE', TRUE, 55);


INSERT INTO alarm_repeat_day (alarm_id, repeat_day)
SELECT alarm_id, 'MON' FROM alarm WHERE member_id = 2;

INSERT INTO alarm_repeat_day (alarm_id, repeat_day)
SELECT alarm_id, 'TUE' FROM alarm WHERE member_id = 2;

INSERT INTO alarm_repeat_day (alarm_id, repeat_day)
SELECT alarm_id, 'WED' FROM alarm WHERE member_id = 2;

INSERT INTO alarm_repeat_day (alarm_id, repeat_day)
SELECT alarm_id, 'THU' FROM alarm WHERE member_id = 2;

INSERT INTO alarm_repeat_day (alarm_id, repeat_day)
SELECT alarm_id, 'FRI' FROM alarm WHERE member_id = 2;



INSERT INTO alarm_snooze (alarm_id, is_enabled, interval_sec, max_count)
SELECT alarm_id, TRUE, 300, 3 FROM alarm WHERE member_id = 2;


INSERT INTO alarm_mission (alarm_id, mission_type, difficulty, walk_goal_meter, question_count)
SELECT alarm_id, 'MATH', 'EASY', NULL, 2 FROM alarm WHERE label = '출근 알람';

INSERT INTO alarm_mission (alarm_id, mission_type, difficulty, walk_goal_meter, question_count)
SELECT alarm_id, 'OX_QUIZ', 'MEDIUM', NULL, 3 FROM alarm WHERE label = '공부 시작';

INSERT INTO alarm_mission (alarm_id, mission_type, difficulty, walk_goal_meter, question_count)
SELECT alarm_id, 'TYPING', 'EASY', NULL, 2 FROM alarm WHERE label = '독서 시간';

INSERT INTO alarm_mission (alarm_id, mission_type, difficulty, walk_goal_meter, question_count)
SELECT alarm_id, 'WALK', NULL, 300, 1 FROM alarm WHERE label = '아침 산책';

INSERT INTO alarm_mission (alarm_id, mission_type, difficulty, walk_goal_meter, question_count)
SELECT alarm_id, 'NONE', NULL, NULL, 0 FROM alarm WHERE label = '카페인 타임';



INSERT INTO alarm_log (alarm_id, triggered_at)
SELECT alarm_id, NOW() - INTERVAL FLOOR(RAND()*10) DAY
FROM alarm WHERE member_id = 2;

INSERT INTO alarm_log (alarm_id, triggered_at)
SELECT alarm_id, NOW() - INTERVAL FLOOR(RAND()*5) DAY
FROM alarm WHERE member_id = 2;



INSERT INTO mission_history (alarm_id, mission_type, is_success, solved_count, total_count)
SELECT a.alarm_id,
       am.mission_type,
       TRUE,
       am.question_count,
       am.question_count
FROM alarm a
         JOIN alarm_mission am ON a.alarm_id = am.alarm_id
WHERE a.member_id = 2;

INSERT INTO mission_history (alarm_id, mission_type, is_success, solved_count, total_count)
SELECT a.alarm_id,
       am.mission_type,
       FALSE,
       FLOOR(am.question_count / 2),
       am.question_count
FROM alarm a
         JOIN alarm_mission am ON a.alarm_id = am.alarm_id
WHERE a.member_id = 2;
