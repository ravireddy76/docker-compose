rs.initiate({
  _id: 'configserver',
  configsvr: true,
  version: 1,
  members: [
    {
      _id: 0,
      host: 'config:27017',
    },
  ],
});