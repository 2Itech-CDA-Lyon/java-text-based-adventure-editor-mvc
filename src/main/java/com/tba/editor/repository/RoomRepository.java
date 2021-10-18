package com.tba.editor.repository;

import com.tba.editor.entity.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> { }
