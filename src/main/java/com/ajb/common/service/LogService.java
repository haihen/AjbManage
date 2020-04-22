package com.ajb.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ajb.common.domain.LogDO;
import com.ajb.common.domain.PageDO;
import com.ajb.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
