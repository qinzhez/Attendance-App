package com.attendU.dev.microservices.room;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Qualifier;

import com.attendU.dev.microservices.bean.Room;

public interface RoomDao {
	@Select("select * from Rooms where adminId =#{adminId}")
	List<Room> findRoomByAdmin(int uid);
	
	@Select("select * from Rooms where rid =#{rid}")
	Room findRoomById(int rid);
	
	@Insert("insert into Rooms (name, participantNum, rcid) \n" + 
			"        values(#{name},#{participantNum},#{rcid})")
	void createRoom(Room room);
	
	@Delete("delete from Rooms where rid = #{rid}")
	void removeRoom(int rid);

	void updateRoom(Room room);
	
	
}
