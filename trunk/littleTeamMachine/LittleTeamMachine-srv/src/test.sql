select * from TaskType tt where 
	(tt.finished=? and (this_.visibility=? and this_.user_id=?)) 
and (this_.finished=? and (this_.visibility=? and this_.group_id=?)) 
and (this_.finished=? and (this_.visibility=? and this_.group_id in ()))