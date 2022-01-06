function getPaginatedItems(items, page, pageSize) {
	var pg = page || 1, pgSize = pageSize || 100, offset = (pg - 1) * pgSize, pagedItems = _.drop(items, offset).slice(0, pgSize);
	return {
		page : pg,
		pageSize : pgSize,
		total : items.length,
		total_pages : Math.ceil(items.length / pgSize),
		data : pagedItems
	};
}