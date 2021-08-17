import 'dart:io';

import 'package:flutter_module/api/api_service.dart';
import 'package:flutter_module/model/news_model.dart';
import 'package:flutter_module/provider/paging_list_model.dart';

class NewsPageModel extends PagingListModel<NewsItemModel, NewsModel> {

  @override
  String getUrl() {
    String deviceModel = Platform.isAndroid ? "Android" : "IOS";
    return ApiService.news_url + deviceModel;
  }

  @override
  String getNextUrl(NewsModel model) {
    String deviceModel = Platform.isAndroid ? "Android" : "IOS";
    return "${model.nextPageUrl}&vc=6030000&deviceModel=$deviceModel";
  }

  @override
  NewsModel getModel(Map<String, dynamic> json) {
    return NewsModel.fromJson(json);
  }

}
