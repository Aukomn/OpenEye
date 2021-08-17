
import 'package:flutter_module/api/api_service.dart';
import 'package:flutter_module/model/topic_model.dart';
import 'package:flutter_module/provider/paging_list_model.dart';

class TopicPageModel extends PagingListModel<TopicItemModel,TopicModel>{

  @override
  String getUrl() {
    return ApiService.topics_url;
  }

  @override
  TopicModel getModel(Map<String, dynamic> json) {
    return TopicModel.fromJson(json);
  }

}