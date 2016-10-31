# Hadoop Cluster Setup
```yaml
- hosts: awsslaves
  vars:
    hadoop_download_src: http://mirror.rise.ph/apache/hadoop/common/hadoop-3.0.0-alpha1/hadoop-3.0.0-alpha1.tar.gz
    hadoop_download_checksum: sha512:71130962DC31436930B787760F1076C74DB745B18A44D032C7CDC9F14227F5F4202CFBE71645F3A57964B31D229951DD68DDACEC7C564CDB8718687A29C41812
    hadoop_download_dest: /home/ec2-user/hadoop-3.0.0-alpha1.tar.gz
    hadoop_install_dir: /home/ec2-user
  tasks:
  - name: install java8
    yum: name=java-1.8.0 state=latest
  - name: remove java7
    yum: name=java-1.7.0-openjdk state=absent
  - name: download hadoop
    get_url:
      url: "{{ hadoop_download_src }}"
      dest: "{{ hadoop_download_dest }}"
      checksum: "{{ hadoop_download_checksum }}"
  - name: extract hadoop
    unarchive: src="{{ hadoop_download_dest }}" dest="{{ hadoop_install_dir }}" copy=no
```
