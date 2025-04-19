import React, { useState, useEffect } from 'react';
import { useLocation,useNavigate } from 'react-router-dom';
import './forum.css';

function Forum() {
  const location = useLocation();
  const { userId, stuId } = location.state || {};
  const [posts, setPosts] = useState([]);
  const [newPostTitle, setNewPostTitle] = useState('');
  const [newPostContent, setNewPostContent] = useState('');
  const [newReplyContent, setNewReplyContent] = useState('');
  const navigate = useNavigate();
  useEffect(() => {
    fetch('http://localhost:8080/tnpbackend/forum')
      .then((response) => response.json())
      .then((data) => setPosts(data))
      .catch((error) => console.error('Error fetching posts:', error));
  }, []);

  const handleNewPost = () => {
    fetch('http://localhost:8080/tnpbackend/forum', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        userId: userId, // Use the actual user ID from state
        postTitle: newPostTitle,
        postContent: newPostContent,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setPosts([...posts, data]);
        setNewPostTitle('');
        setNewPostContent('');
        //navigate('/latestcom', { state: { userId,stuId } });
      })
      .catch((error) => console.error('Error creating post:', error));
  };

  const handleNewReply = (postId) => {
    fetch('http://localhost:8080/tnpbackend/forum/reply', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: new URLSearchParams({
        userId: userId, // Use the actual user ID from state
        postId: postId,
        replyContent: newReplyContent,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        const updatedPosts = posts.map((post) =>
          post.postId === postId ? { ...post, replies: [...post.replies, data] } : post
        );
        setPosts(updatedPosts);
        setNewReplyContent('');
      })
      .catch((error) => console.error('Error creating reply:', error));
  };
  const handleDeletePost = (postId,userId) => {
     fetch('http://localhost:8080/tnpbackend/deletepost', {
       method: 'POST', headers: { 'Content-Type': 
        'application/x-www-form-urlencoded', },
         body: new URLSearchParams({ postId: postId, userId: userId,}), })
          .then((response) => response.text()) .then((data) => { 
            if (data === 'Post deleted successfully') { 
              setPosts(posts.filter((post) => post.postId !== postId)); 
            } else { alert(data); } })
             .catch((error) => alert('Error deleting post:', error)); 
            };

  return (
    <div className="forum-container">
      <h2>Forum</h2>
      <div className="new-post">
        <input
          type="text"
          placeholder="Post Title"
          value={newPostTitle}
          onChange={(e) => setNewPostTitle(e.target.value)}
        />
        <textarea
          placeholder="Post Content"
          value={newPostContent}
          onChange={(e) => setNewPostContent(e.target.value)}
        ></textarea>
        <button onClick={handleNewPost}>Create Post</button>
      </div>
      <div className="posts">
        {posts.length > 0 ? (
          posts.map((post) => (
            <div key={post.postId} className="post">
              <h3>{post.postTitle}</h3>
              <p>{post.postContent}</p>
              <p><strong>Posted by:</strong> {post.username}</p>
              <button onClick={() => handleDeletePost(post.postId,post.userId)}>Delete Post</button>
              <div className="replies">
                {post.replies.map((reply) => (
                  <div key={reply.replyId} className="reply">
                    <p>{reply.replyContent}</p>
                    <p><strong>Replied by:</strong> {reply.username}</p>
                  </div>
                ))}
                <textarea
                  placeholder="Reply Content"
                  value={newReplyContent}
                  onChange={(e) => setNewReplyContent(e.target.value)}
                ></textarea>
                <button onClick={() => handleNewReply(post.postId)}>Reply</button>
              </div>
            </div>
          ))
        ) : (
          <p>No posts available.</p>
        )}
      </div>
    </div>
  );
}

export default Forum;
