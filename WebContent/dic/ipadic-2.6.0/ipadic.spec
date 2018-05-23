%define prefix /usr
%define PACKAGE ipadic
%define VERSION 2.6.0

Summary: Japanese dictionary for ChaSen
Name: %{PACKAGE}
Version: %{VERSION}
Release: 1
Copyright: read COPYRIGHT file
Group: local
URL: http://chasen.aist-nara.ac.jp/
Source: %{name}-%{version}.tar.gz
BuildArchitectures: noarch
BuildRoot: /var/tmp/%{name}
Packager: Taku Kudoh <taku-ku@is.aist-nara.ac.jp>
Requires: chasen >= 2.3.1

%description 
ChaSen �Τ���� IPA�ʻ��ηϤ˴�Ť����ܸ켭��

�����ǲ��ϥ����ƥ���䥤ϡ���������Τ���Υե꡼���եȥ������Ȥ���������
ü�ʳص�����ر���ؤ���������Ƥ��륷���ƥ�Ǥ����ܼ���ϡ����
(version2.3.1�ʹ�)�Ѥ����ܸ켭�� (ipadic2.6.0)�ˤĤ�������������ΤǤ�����
����Ǥϡ���������������ȶ���(IPA)�����ꤵ�줿IPA�ʻ��η�(THiMCO97)�˴�
�Ť��ư���������ä��ޤ�������������Ͽ����������ȯ����(RWCP)�ˤ��֥�
�����ȥǡ����١�������(ʿ����ǯ��)�פ˷Ǻܤ��줿IPA�ʻ��η�(THiMCO97)
����������Ĥ�����ȴ�褷������������ܤ�����ΤǤ���

�ʤ������ߤ�IPA�ʻ��η����ܸ켭��ϡ�1998ǯ5��˸�������IPA�ʻ��η�����
�켭��(ipadic1.0b2)���Ф��ơ�������ü�ʳص�����ر���ؾ���ʳظ���ʼ�
��������������ɽ�Ȥ�������ܸ�ǥ����ơ��������ܥ��եȥ������γ�ȯ��
(IPA����Ū���Ū���󵻽Ѥ˴ؤ�븦�泫ȯ)�Υ��롼�פ������������ʽ�����
���ɤ�ԤäƤ������������θ塤�͡��ʽ�����Ԥä���ΤǤ���

�ܼ��񥷥��ƥ�ι��ۤ˷Ȥ��줿���٤Ƥ������ˤ��Ф��ƿ���괶�դ��ޤ���

�ܼ���˴ؤ��뤪�䤤��碌�ϰʲ��ˤ��ꤤ���ޤ���

��630-0192
���ɸ�����Թ⻳Į8916-5
������ü�ʳص�����ر����
����ʳظ����\ ������������عֺ�

Tel: (0743)72-5240, Fax: (0743)72-5249
E-mail: chasen@is.aist-nara.ac.jp

%prep

%setup

%build
./configure --with-dicdir=%{prefix}/share/chasen/dic --with-chasenrc-path=$RPM_BUILD_ROOT%{prefix}/etc/chasenrc
make

%install
mkdir -p $RPM_BUILD_ROOT%{prefix}/etc
make dicdir=$RPM_BUILD_ROOT%{prefix}/share/chasen/dic/%{name} install

%clean
rm -rf $RPM_BUILD_ROOT

%files
%defattr(-, root, root)
%doc *.texi *.pdf
%{prefix}/lib/chasen/dic/%{name}/*
%config %{prefix}/etc/chasenrc